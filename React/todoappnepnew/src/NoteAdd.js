import { useState } from "react";
import service from "./service";
import "bootstrap/dist/css/bootstrap.min.css";
import alertify from "alertifyjs";
import { useNavigate } from "react-router-dom";
export default function NoteAdd() {
  const [header, setHeader] = useState("");
  const [content, setContent] = useState("");

  const backMainPage = () => {
    navigate("/");
  };

  let navigate = useNavigate();

  const add = () => {
    let note = { header: header, content: content };
    service
      .add(note)
      .then(() => alertify.success("Not Eklendi", 1.5), navigate("/"))
      .catch(() => alertify.danger("Not Eklenmedi", 1.5), navigate("/"));
  };

  return (
    <div className="container card col-md-6 offset-md-3 offset-md-3">
      <button
        type="button"
        class="h-25 d-inline-block"
        onClick={() => backMainPage()}
      ></button>
      <div className="card-body">
        <form>
          <div className="text-center">Not Ekle</div>
          <br />
          <input
            onChange={(e) => setHeader(e.target.value)}
            placeholder="Not Başlığı"
            className="form-control"
          />
          <br />
          <textarea
            onChange={(e) => setContent(e.target.value)}
            placeholder="Not içeriği"
            className="form-control"
          />
          <br />

          <button onClick={() => add()} className="btn btn-success">
            Notu Ekle
          </button>
        </form>
      </div>
    </div>
  );
}
