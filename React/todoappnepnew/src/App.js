import { Route, Routes } from "react-router-dom";
import NoteList from "./NoteList";
import NoteAdd from "./NoteAdd";
import NoteUpdate from "./NoteUpdate";
function App() {
  return (
    <Routes>
      <Route path="/" element={<NoteList />} />
      <Route path="/add" element={<NoteAdd />} />
      <Route exact path="/update/:id" element={<NoteUpdate authed={true} />} />
    </Routes>
  );
}

export default App;
