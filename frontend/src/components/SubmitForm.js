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

  if (selectedBook) {
    saveButton.disabled = false;
    deleteButton.disabled = false;
  }

  const handleSubmit = async (event) => {
    const { author, title, description } = formData;
    var res = "null";

    if (event.nativeEvent.submitter.value === "Save new") {
      // POST request
      res = await axios
        .post("/books", JSON.stringify({ title, author, description }), {
          headers: {
            // Overwrite Axios's automatically set Content-Type
            "Content-Type": "application/json",
          },
        })
        .catch(function (error) {
          if (error.response) {
            alert(
              error.response.data,
              error.response.status,
              error.response.headers
            );
          } else if (error.request) {
            alert(error.request);
          }
        });
    } else if (event.nativeEvent.submitter.value === "Delete") {
      // DELETE request
      res = await axios
        .delete(`/book?id=${formData.id}`)
        .catch(function (error) {
          if (error.response) {
            alert(
              error.response.data,
              error.response.status,
              error.response.headers
            );
          } else if (error.request) {
            alert(error.request);
          }
        });
    } else if (event.nativeEvent.submitter.value === "Save") {
      // PUT request
      res = await axios
        .put(
          `/book`,
          JSON.stringify({ id: formData.id, title, author, description }),
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .catch(function (error) {
          if (error.response) {
            alert(
              error.response.data,
              error.response.status,
              error.response.headers
            );
          } else if (error.request) {
            alert(error.request);
          }
        });
    }
    alert(res.data);
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
          className="Submit"
          type="text"
          name="title"
          value={formData.title}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <p>Author</p>
        <input
          className="Submit"
          type="text"
          name="author"
          value={formData.author}
          onChange={handleInputChange}
        />
      </div>
      <div className="Submit">
        <p>Description</p>
        <textarea
          className="Submit"
          name="description"
          value={formData.description}
          onChange={handleInputChange}
        />
      </div>
      <input type="submit" className="button" value="Save new" />
      <input
        type="submit"
        className="button"
        id="saveButton"
        value="Save"
        disabled
      />
      <input
        type="submit"
        className="button"
        id="deleteButton"
        value="Delete"
        disabled
      />
    </form>
  );
}

export default SubmitForm;
