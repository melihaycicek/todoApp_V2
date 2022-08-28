package com.todoapp.todoapp.controller;


import java.sql.SQLException;
        import java.util.ArrayList;
import java.util.List;
        import java.util.Map;
        import java.util.Optional;


import com.todoapp.todoapp.model.Todoinfo;
import com.todoapp.todoapp.model.User;
import com.todoapp.todoapp.repository.TodoinfoRepository;
import com.todoapp.todoapp.repository.UserRepository;
import com.todoapp.todoapp.service.Impl.TodoinfoServiceImp;
import com.todoapp.todoapp.service.Impl.UserServiceImp;
import com.todoapp.todoapp.util.ApiPaths;
import org.springframework.beans.factory.annotation.Autowired;

        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping(ApiPaths.TodoinfoBasicCtrl.CTRL)
public class TodoinfoController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    TodoinfoServiceImp todoinfoServiceImp;
    @Autowired
    TodoinfoRepository todoinfoRepository;

    @RequestMapping(value = "/show-todoinfos/{userid}", method = RequestMethod.GET)
    public String TodoinfosShowPanel(@PathVariable int userid, Map<String, Object> map) throws SQLException {

        Optional<User> user = userServiceImp.findById(userid);
        if(user.isPresent()) {

            List<Todoinfo> todoinfos = todoinfoServiceImp.findByUser(user.get());
            map.put("header", "Müşteri Ait Hayvanlar");
            map.put("user", user.get());
            map.put("todoinfo", new Todoinfo());
            map.put("todoinfos", todoinfos);
            return "todoinfo/todoinfos";
        }else {
            List<User> users = userServiceImp.findAll();
            map.put("header", "Kullanıcılar");
            map.put("users", users);
            map.put("message", " Kayıt bulunamamıştır.");
            return "user/users";
        }
    }
    @RequestMapping(value = "/show-todoinfos-by-name/{userid}", method = RequestMethod.GET)
    public String TodoinfosShowPanel2(@RequestParam("name") String name,@PathVariable int userid, Map<String, Object> map) throws SQLException {

        List<Todoinfo> todoinfos=new ArrayList<>();
        Optional<User> user = userServiceImp.findById(userid);
        if(user.isPresent()) {
            List<Todoinfo> todoinfos2 = todoinfoServiceImp.findByUser(user.get());
            todoinfos2.stream().forEach(item-> {
                if(item.getName().equals(name))	{
                    item=item;
                    System.out.println(item.getId()+" : "+item.getName()+" "+item.getContent());
                    todoinfos.add(item);
                }
            });
            if(todoinfos.size()>0) {
                map.put("todoinfos",todoinfos);
                map.put("message","Kayıtlar bulunmuştur");
            }
            else {
                map.put("message", name+" isme ait bir hayvan bulunamamıştır.");
                todoinfos2 = todoinfoServiceImp.findByUser(user.get());
                map.put("todoinfos", todoinfos2);
            }
            map.put("header", "Kullanıcı Bilgileri");
            map.put("user", user.get());
            return "todoinfo/todoinfos";
        }else {
            List<User> users = userServiceImp.findAll();
            map.put("header", "Kullanıcılar");
            map.put("users", users);
            map.put("message", "Bu isimde bir kayıt yoktur. id : " + userid);
            return "user/users";
        }

    }


    @RequestMapping(value = "/insert-todoinfo/{userid}", method = RequestMethod.GET)
    public String InsertTodoinfoPanel(@PathVariable int userid, Map<String, Object> map,
                                 @ModelAttribute("todoinfo") Todoinfo todoinfo, BindingResult result, Model model)  throws SQLException {

        Optional<User> user = userServiceImp.findById(userid);
        if(user.isPresent()) {
            List<Todoinfo> todoinfos = todoinfoServiceImp.findByUser(user.get());
            map.put("header", "Kullanıcı detayları");
            map.put("user", user.get());
            map.put("todoinfo", new Todoinfo());
            map.put("todoinfos", todoinfos);


            return "todoinfo/todoinfo-insert-panel";
        }else {
            List<User> users = userServiceImp.findAll();
            map.put("header", "Kullanıcılar");
            map.put("users", users);
            map.put("message", " Kayıt bulunamamıştır.");
            return "user/users";
        }

    }
    @RequestMapping(value = "/insert-todoinfo/{userid}", method = RequestMethod.POST)
    public String InsertTodoinfo(@PathVariable int userid, Map<String, Object> map,
                            @ModelAttribute("todoinfo") Todoinfo todoinfo, BindingResult result, Model model)  throws SQLException {

        if (!todoinfo.getName().equals("") || !todoinfo.getContent().equals("") ) {
            Optional<User> user = userServiceImp.findById(userid);
            if(user.isPresent()) {
                if (result.hasErrors()) {
                    map.put("message", " Bir problem oluştu.");

                } else {
                    todoinfo.setUser(user.get());
                    todoinfoRepository.save(todoinfo);
                    map.put("message", "Kayıt işlemi başarılı");
                }
                List<Todoinfo> todoinfos = todoinfoServiceImp.findByUser(user.get());
                map.put("user", user.get());
                map.put("todoinfo", new Todoinfo());
                map.put("todoinfos", todoinfos);
                map.put("header", "Müşteri Ait Hayvanlar");
                return "todoinfo/todoinfos";
            }else {
                List<User> users = userRepository.findAll();
                map.put("header", "Kullanıcılar");
                map.put("users", users);
                map.put("message", " Kayıt bulunamamıştır.");
                return "user/users";
            }
        } else {
            Optional<User> user = userServiceImp.findById(userid);
            if(user.isPresent()) {
                List<Todoinfo> todoinfos = todoinfoServiceImp.findByUser(user.get());
                map.put("user", user.get());
                map.put("todoinfo", new Todoinfo());
                map.put("message", "Boş alanları doldurunuz");
                map.put("todoinfos", todoinfos);

                return "user/show-user";
            } else {
                List<User> users = userRepository.findAll();
                map.put("header", "Kullanıcılar");
                map.put("users", users);
                map.put("message", "Boş alanları doldurunuz");
                return "user/users";
            }
        }
    }


    @RequestMapping(value = "/delete-todoinfo/{todoinfo_id}", method = RequestMethod.GET)
    public String DeleteTodoinfo(@PathVariable long todoinfo_id, Map<String, Object> map,  @ModelAttribute("todoinfo") Todoinfo todoinfo,
                            BindingResult result, Model model) throws SQLException {

        Optional<Todoinfo> selected_todoinfo = todoinfoServiceImp.findById(todoinfo_id);
        if(selected_todoinfo.isPresent()) {
            int userid = selected_todoinfo.get().getUser().getUserid();
            selected_todoinfo.get().setUser(null);
            Boolean control=todoinfoServiceImp.delete(selected_todoinfo.get());
            if(control==true) {
                map.put("message","Kayıt başarıyla silinmiştir.");
            }else {
                map.put("message","Bir hata oluştu.");
            }
            User user = userRepository.findById(userid).get();

            List<Todoinfo> todoinfos = todoinfoRepository.findByUser(user);
            map.put("header", "Kullanıcıya ait bilgiler");
            map.put("user", user);
            map.put("todoinfo", new Todoinfo());
            map.put("todoinfos", todoinfos);
            return "todoinfo/todoinfos";
        }else {
            List<User> users = userRepository.findAll();
            map.put("header", "Kullanıcılar");
            map.put("users", users);
            map.put("message","Kullanıcı bilgi kaydına ulaşılamamıştır");
            return "user/users";
        }


    }

}