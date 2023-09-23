import React, { useState, useEffect } from "react";
import axios from "axios";

function SubmitForm({ selectedBook }) {
  const [formData, setFormData] = useState({
    title: "",
    author: "",
    description: "",
  });
  const saveButton = document.getElementById("saveButton");
  const deleteButton = document.getElementById("deleteButton");

  // TO-DO implement enabling of savebutton, deletebutton if an item has been selected
  // TO-DO implement some way of clearing chosen item so that users can cancel operation (button?)

  useEffect(() => {
    if (selectedBook) {
      setFormData({
        id: selectedBook.id,
        author: selectedBook.author,
        title: selectedBook.title,
        description: selectedBook.description || "",
      });
    } else {
      setFormData({
        id: "",
        author: "",
        title: "",
        description: "",
      });
    }
  }, [selectedBook]);

  const handleSubmit = async (event) => {
    const { author, title, description } = formData;

    if (author.length === 0 || title.length === 0 || description.length === 0) {
      event.preventDefault(); // Prevent default form submission
      alert("All fields must be filled out!");
      return;
    }

    if (!isNaN(+author) || !isNaN(+title) || !isNaN(+description)) {
      event.preventDefault(); // Prevent default form submission
      alert("Values must be strings!");
      return;
    }

    alert(
      "author: " +
        author +
        "\ntitle: " +
        title +
        "\ndescription: " +
        description +
        "\n Added to the book list"
    );
    var res = "null";

    if (event.nativeEvent.submitter.value === "Save new") {
      // POST request
      res = await axios.post(
        "/books",
        JSON.stringify({ author, title, description }),
        {
          headers: {
            // Overwrite Axios's automatically set Content-Type
            "Content-Type": "application/json",
          },
        }
      );
    } else if (event.nativeEvent.submitter.value === "Delete") {
      // DELETE request
      res = await axios.delete(
        `/book?id=${formData.id}`,
        JSON.stringify({ author, title, description }),
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
    }
    console.log(res);
    // Handle your POST or DELETE request here based on the submit button value
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <p>Title</p>
        <input
          type="text"
          name="title"
          value={formData.title}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <p>Author</p>
        <input
          type="text"
          name="author"
          value={formData.author}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <p>Description</p>
        <textarea
          name="description"
          value={formData.description}
          onChange={handleInputChange}
        />
      </div>
      <input type="submit" value="Save new" />
      <input type="submit" id="saveButton" value="Save" disabled />
      <input type="submit" id="deleteButton" value="Delete" disabled />
    </form>
  );
}

export default SubmitForm;
