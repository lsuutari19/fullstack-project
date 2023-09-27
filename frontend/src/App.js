import './App.css';
import { useState, React } from "react";
import GetBooks from './components/GetBooks';
import SubmitForm from './components/SubmitForm';

function App() {
  const [selectedBook, setSelectedBook] = useState(null);
  return (
    <div className="App">
      <div className="Submit">
        <SubmitForm selectedBook={selectedBook}></SubmitForm>
      </div>
      <div className="Books">
        <GetBooks setSelectedBook={setSelectedBook}></GetBooks>
      </div>
    </div>
  );
}

export default App;
