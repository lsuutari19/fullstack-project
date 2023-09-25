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

  if(selectedBook) {
    saveButton.disabled = false;
    deleteButton.disabled = false;
  }

  const handleSubmit = async (event) => {
    const { author, title, description } = formData;
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
      alert("id" + formData.id)
      res = await axios.delete(
        `/book?id=${formData.id}`
      );
    } else if (event.nativeEvent.submitter.value === "Save") {
      // PUT request
      alert("id" + formData.id)
      res = await axios.put(
        `/book`,
        JSON.stringify({ id: formData.id, author, title, description }),
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
    }
    alert(res);
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
