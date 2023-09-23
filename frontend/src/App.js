import './App.css';
import { useState, React } from "react";
import GetBooks from './components/GetBooks';
import SubmitForm from './components/SubmitForm';

function App() {
  const [selectedBook, setSelectedBook] = useState(null);
  return (
    <div className="App">
      <SubmitForm selectedBook={selectedBook}></SubmitForm>
      <GetBooks setSelectedBook={setSelectedBook}></GetBooks>
    </div>
  );
}

export default App;
