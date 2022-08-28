package com.todoapp.todoapp.Manager;

import com.todoapp.todoapp.Entity.Note;
import com.todoapp.todoapp.IBusiness.INoteBuesiness;
import com.todoapp.todoapp.IData.INoteData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service //Service katmanı oldugunu belirmtek için
public class NoteManager implements INoteBuesiness {
    @Autowired
    private INoteData noteData; //Interface e baglamak icin olusturduk. Viper jrp arp oluşturalım

    public NoteManager(INoteData dateData) {
        super();
        this.noteData = noteData;
    }

    @Override
    @Transactional
    public void add(Note note) {



        // note.setDate(date);
        this.noteData.save(note);

    }

    @Override
    @Transactional
    public void update(Note note) {



    this.noteData.save(note);  //Control of Id
    }

    @Override
    @Transactional
    public void delete(Note note) {
        this.noteData.delete(note);
    }

    @Override
    @Transactional
    public List<Note> getAll() {
        return this.noteData.findAll();
    }
@Override
@Transactional
    public Optional<Note> getById(String id) {
        return this.noteData.findById(id);//Gelen verinin idsini alır jpa repo arkasında id yi alır sqldeki id lerle karşılaktırır
    }//Getmappingde slashtan sonraki veriyi id olarak çekiyoruz path variable ile bu veriyi string id ye aktarıp eşleştirme yapıyoruz.
}/*
return this.noteData.findById(id);  JPa repo arkasında olan
id yi kullanmaya yarıyor ve postgrldeki id leri kontrol edip
veriyi geri dönderiyor.
*/
