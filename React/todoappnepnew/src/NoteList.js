import "./App.css";
import { useEffect, useState } from "react";
import service from "./service";
import { Table } from "reactstrap";
import { useNavigate } from "react-router-dom";
import alertify from "alertifyjs";

function NoteList() {
  const [note, setNote] = useState([]);
  let navigate = useNavigate();
  useEffect(() => {
    service.liste().then((result) => {
      setNote(result.data);
    });
  }, []);

  const addNote = () => {
    navigate("/add");
  };

  const updateNote = (id) => {
    navigate(`/update/${id}`);
  };

  const deleteNot = (id) => {
    let del = { id }; //JSONA dönderip değişkene aktardık.
    service.delete(del).then(alertify.success("Not Silindi", 2)).catch();
  };

  return (
    <div>
      <button onClick={() => addNote()} className="btn btn-success">
        Yeni Not
      </button>

      <Table>
        <thead>
          <tr>
            <th>#</th>
            <th>Baslik</th>
            <th>Icerik</th>
            <th>Tarih</th>
          </tr>
        </thead>
        <tbody>
          {note.map((not, index) => (
            <tr key={not.id}>
              <th scoper="row">{index}</th>
              <td>{not.header}</td>
              <td>{not.content}</td>
              <td>{not.date}</td>
              <td>
                <button
                  onClick={() => deleteNot(not.id)}
                  className="btn btn-danger"
                >
                  sil
                </button>
              </td>
              <td>
                <button
                  onClick={() => updateNote(not.id)}
                  className="btn btn-success"
                >
                  Notu Düzenle
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
}

export default NoteList;
