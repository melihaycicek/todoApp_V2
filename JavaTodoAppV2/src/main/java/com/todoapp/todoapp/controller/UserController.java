package com.todoapp.todoapp.controller;



import java.sql.SQLException;
import java.util.List;
        import java.util.Map;
        import java.util.Optional;


import com.todoapp.todoapp.model.User;
import com.todoapp.todoapp.repository.TodoinfoRepository;
import com.todoapp.todoapp.service.Impl.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.PageRequest;

        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.ResponseBody;




@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    TodoinfoRepository todoInfoRepository;
    @Autowired
    UserServiceImp userServiceImp;


/////REQUEST MAPPING FOR USER

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAllUsers(Map<String, Object> map) {
        List<User> users = userServiceImp.findAll();
        System.out.println("Kullanıcılar");
        System.out.println(users);
        map.put("header", "Kullanıcılar");
        map.put("users", users);
        return "user/users";
    }




    //http://localhost:8182/user/users-page?page=1&size=3
    @ResponseBody
    @RequestMapping(value = "/users-page", method = RequestMethod.GET)
    public String getAllUsersPage(Map<String, Object> map,
                                      @RequestParam("page") Optional<Integer> page,
                                      @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(5);
        Page<User> users = userServiceImp.GetAllPagination(PageRequest.of(currentPage - 1, pageSize));
        map.put("header", "Kullanıcılar");
        map.put("users", users);
        users.getContent().forEach(aa->{
            System.out.println(aa.getUserid()+" "+aa.getEmail());
        });
        return "currentPage : "+currentPage +" <br>"
                +"pageSize : "+ pageSize +" <br>";
    }





    @RequestMapping(value = "/get-users", method = RequestMethod.GET)
    public String getAnyUsers(@RequestParam("name") String name,Map<String, Object> map) {
        map.put("title", "Kullanıcılar");
        List<User> users=null;
        users = userServiceImp.findByUsername(name);

        //referansa dair  herhangi bir username olup olmadığını kontrol eder.
        if(users.size()>0) {
            map.put("users", users);
        }else {
            //referansı verilen  bu emaile sahip herhangi bir kullanıcı olup olmadığını kontrol eder.
            users = userServiceImp.findByEmail(name);
            if(users.size()>0) {
                map.put("users", users);
            }else {
                users = userServiceImp.findAll();
                map.put("users", users);
                map.put("message", " Kayıt bulunamamıştır.");
            }
        }
        return "user/users";
    }





    @RequestMapping(value = "/insert-user", method = RequestMethod.GET)
    public String UserRegisterPanel(Map<String, Object> map) {
        map.put("header", "Kullanıcı Ekleme");
        map.put("user", new User());
        return "user/user-insert-panel";
    }

    @RequestMapping(value = "/insert-user", method = RequestMethod.POST)
    public String saveRegisterPage( @ModelAttribute("user") User user, BindingResult result,
                                    Model model, Map<String, Object> map) {
        map.put("user", new User());
        map.put("header", "Kullanıcı Ekleme");
        if (result.hasErrors()) {
            return "user-insert-panel";
        } else {
            Boolean control=userServiceImp.save(user);
            if(control==false) {
                map.put("message", "Bir Hata Söz konusu...");
            }else {
                map.put("message", "Kaydınız Tamamlandı...");
            }
        }

        return "user/user-insert-panel";
    }

    @RequestMapping(value = "/show-user/{userid}", method = RequestMethod.GET)
    public String UserShowPanel(@PathVariable int userid, Map<String, Object> map) throws SQLException {

        Optional<User> user = userServiceImp.findById(userid);
        if(user.isPresent()) {
            map.put("header", "Kullanıcı bilgileri");
            map.put("user", user.get());

            return "user/show-user";
        }else {
            List<User> users = userServiceImp.findAll();
            map.put("header", "Kullanıcılar");
            map.put("users", users);
            map.put("message", " Kayıt bulunamamıştır.");
            return "user/users";
        }
    }

    ///UPDATE USER   FOR POST METHOD

    @RequestMapping(value = "/update-users/{userid}", method = RequestMethod.GET)
    public String UserUpdatePanel(@PathVariable int userid, Map<String, Object> map)  throws SQLException {


        Optional<User> user = userServiceImp.findById(userid);
        if(user.isPresent()) {
            map.put("header", "Kullanıcı Güncelleme");
            map.put("user", user.get());
            return "user/users-update-panel";
        }else {
            List<User> users = userServiceImp.findAll();
            map.put("title", "Müşteriler");
            map.put("users", users);
            map.put("message", " Kayıt bulunamamıştır.");
            return "users/users";
        }

    }

    ///UPDATE USER   FOR POST METHOD

    @RequestMapping(value = "/update-users", method = RequestMethod.POST)
    public String UserUpdate( @ModelAttribute("user") User user, BindingResult result,
                                  Map<String, Object> map)  throws SQLException {
        map.put("user", new User());
        if (result.hasErrors()) {
            map.put("header", "Kullanıcı Ekleme");
            return "user-insert-panel";
        } else {
            Boolean control=userServiceImp.save(user);
            if(control==false) {
                map.put("message", "Sanırım bi hata oluştu..");
            }else {
                map.put("message", "Kaydın güncellendi bi kontrol etsene..");
            }
        }
        List<User> users = userServiceImp.findAll();
        map.put("header", "Kullanıcılar");
        map.put("users", users);
        return "user/users";
    }
    @RequestMapping(value = "/delete-users/{userid}", method = RequestMethod.GET)
    public String UserDelete(@PathVariable int userid, Map<String, Object> map) throws SQLException {

        Optional<User> user = userServiceImp.findById(userid);
        if(user.isPresent()) {
            Boolean control=userServiceImp.delete(user.get());
            if(control == true) {
                map.put("message", "Kayıt silinmiştir.");
            }else {
                map.put("message", " Kayıt silme işlemi başarısız.");
            }
        }else {
            map.put("message", " Eşleşen kayıt bulunamadı...");

        }
        List<User> users = userServiceImp.findAll();
        map.put("header", "Müşteriler");
        map.put("users", users);
        return "user/users";
    }


}