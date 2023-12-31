import React, { useEffect, useState } from "react";
import axios from "axios";

function GetBooks({ setSelectedBook }) {
const [books, setBooks] = useState([]);

  const handleBookSelect = (book) => {
    setSelectedBook(book);
  }
  // TO-DO: implement error handling
  const GetBookData = async () => {
    const { data } = await axios.get("/books");
    console.log(data);
    setBooks(data);
  };

  useEffect(() => {
    GetBookData();
  }, []);

  // implement selection of each item for deletion/modifying
  return (
    <div className="Table">
      <p>List of books:</p>
        {books.map((book, index) => (
            <ul key= {index} onClick={() => handleBookSelect(book)}>
                <p className="Book">
                    Title: {book.title} <br></br> Author: {book.author} <br></br> Description: {book.description}
                </p>
            </ul> 
        ))}
    </div>
  );
}

export default GetBooks;
