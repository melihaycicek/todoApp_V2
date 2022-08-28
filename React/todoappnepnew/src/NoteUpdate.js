import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import Modal from "react-modal";
import { useNavigate } from "react-router-dom";
import "./index.scss";
import { GrFormClose } from "react-icons/gr";
import service from "./service";
import alertify from "alertifyjs";

export default function NoteUpdate(props) {
  const [header, setHeader] = useState("");
  const [content, setContent] = useState("");
  const [modalIsOpen, setModalIsOpen] = useState(true);
  let navigate = useNavigate();
  const { id } = useParams();

  const toggleModal = () => {
    setModalIsOpen(!modalIsOpen);
    navigate("/");
  };

  useEffect(() => {
    service.getById(id).then((res) => {
      let note = res.data;
      setHeader(note.title);
      setContent(note.contents);
    });
  }, []); //Başlıkta boşlukltan sonrası için ekleriz yoksa sürekli boişluk ekleme kısmında bir sonraki cümleye izin vermez gibi düşüm..

  const update = () => {
    let note = { id: id, header: header, content: content };
    service
      .update(note)
      .then(() => alertify.success("Not Düzenlendi", 1.5), navigate("/"))
      .catch(() => alertify.danger("Not Düzenlenemdi", 1.5), navigate("/"));
  };

  return (
    <div className="container card col-md-6 offset-md-3 offset-md-3">
      <Modal
        isOpen={modalIsOpen}
        onRequestClose={toggleModal}
        className="about-modal"
        overlayClassName="about-modal-overlay"
      >
        <button onClick={toggleModal} className="btn btn-danger">
          <GrFormClose />
        </button>

        <div className="card-body">
          <form>
            <div className="text-center">Notu Düzenle</div>
            <br />
            <input
              placeholder="Not Başlığı Giriniz"
              onChange={(e) => setHeader(e.target.value)}
              value={header}
              className="form-control"
            />
            <br />
            <textarea
              placeholder="Not İçeriği Giriniz"
              onChange={(e) => setContent(e.target.value)}
              value={content}
              className="form-control"
            />
            <br />
            <button onClick={() => update()} className="btn btn-success">
              Notu Kaydet
            </button>
          </form>
        </div>
      </Modal>
    </div>
  );
}
