import React, { useState, useEffect } from "react";
import BookService from "./BookService";

function App() {
  const [books, setBooks] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [isEditing, setIsEditing] = useState(false);
  const [currentBookId, setCurrentBookId] = useState(null);

  const [formData, setFormData] = useState({
    title: "",
    author: "",
    isbn: "",
    publicationYear: 2024,
    isAvailable: true,
  });

  useEffect(() => {
    fetchBooks();
  }, []);

  const fetchBooks = () => {
    BookService.getAllBooks().then((res) => setBooks(res.data));
  };

  const handleInputChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({ ...formData, [name]: type === "checkbox" ? checked : value });
  };

  // Prepare the form for editing
  const handleEditClick = (book) => {
    setIsEditing(true);
    setCurrentBookId(book.id);
    setFormData({
      title: book.title,
      author: book.author,
      isbn: book.isbn,
      publicationYear: book.publicationYear,
      isAvailable: book.isAvailable,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (isEditing) {
      // Calls: @PutMapping("/{id}")
      BookService.updateBook(currentBookId, formData).then(() => {
        resetForm();
        fetchBooks();
      });
    } else {
      // Calls: @PostMapping
      BookService.addBook(formData).then(() => {
        resetForm();
        fetchBooks();
      });
    }
  };

  const resetForm = () => {
    setIsEditing(false);
    setCurrentBookId(null);
    setFormData({
      title: "",
      author: "",
      isbn: "",
      publicationYear: 2024,
      isAvailable: true,
    });
  };

  const handleDelete = (id) => {
    if (window.confirm("Are you sure?")) {
      BookService.deleteBook(id).then(() => fetchBooks());
    }
  };

  const filteredBooks = books.filter(
    (b) =>
      b.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
      b.author.toLowerCase().includes(searchTerm.toLowerCase()),
  );

  return (
    <div className="min-h-screen bg-gray-50 p-8">
      <div className="max-w-6xl mx-auto">
        <div className="flex justify-between mb-8">
          <h1 className="text-2xl font-bold">Book Inventory</h1>
          <input
            type="text"
            placeholder="Search..."
            className="border p-2 rounded-md w-64"
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </div>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          {/* FORM PANEL */}
          <div className="bg-white p-6 rounded-lg shadow-sm border">
            <h2 className="text-lg font-bold mb-4">
              {isEditing ? "Edit Book" : "Add New Book"}
            </h2>
            <form onSubmit={handleSubmit} className="space-y-4">
              <input
                name="title"
                placeholder="Title"
                value={formData.title}
                onChange={handleInputChange}
                required
                className="w-full border p-2 rounded"
              />
              <input
                name="author"
                placeholder="Author"
                value={formData.author}
                onChange={handleInputChange}
                required
                className="w-full border p-2 rounded"
              />
              <input
                name="isbn"
                placeholder="ISBN"
                value={formData.isbn}
                onChange={handleInputChange}
                required
                className="w-full border p-2 rounded"
              />
              <input
                type="number"
                name="publicationYear"
                value={formData.publicationYear}
                onChange={handleInputChange}
                required
                className="w-full border p-2 rounded"
              />

              <div className="flex items-center gap-2">
                <input
                  type="checkbox"
                  name="isAvailable"
                  checked={formData.isAvailable}
                  onChange={handleInputChange}
                />
                <label>Available</label>
              </div>

              <button
                type="submit"
                className={`w-full py-2 rounded text-white ${isEditing ? "bg-orange-500" : "bg-blue-600"}`}
              >
                {isEditing ? "Update Book" : "Save Book"}
              </button>
              {isEditing && (
                <button
                  type="button"
                  onClick={resetForm}
                  className="w-full text-gray-500 text-sm mt-2"
                >
                  Cancel Edit
                </button>
              )}
            </form>
          </div>

          {/* LIST PANEL */}
          <div className="md:col-span-2 bg-white rounded-lg shadow-sm border overflow-hidden">
            <table className="w-full text-left">
              <thead className="bg-gray-100">
                <tr>
                  <th className="p-4">Book Details</th>
                  <th className="p-4">Status</th>
                  <th className="p-4 text-right">Actions</th>
                </tr>
              </thead>
              <tbody>
                {filteredBooks.map((book) => (
                  <tr key={book.id} className="border-t hover:bg-gray-50">
                    <td className="p-4">
                      <div className="font-bold">{book.title}</div>
                      <div className="text-sm text-gray-500">
                        {book.author} | {book.isbn}
                      </div>
                    </td>
                    <td className="p-4">
                      <span
                        className={`text-xs px-2 py-1 rounded-full ${book.isAvailable ? "bg-green-100 text-green-700" : "bg-red-100 text-red-700"}`}
                      >
                        {book.isAvailable ? "Available" : "Borrowed"}
                      </span>
                    </td>
                    <td className="p-4 text-right space-x-2">
                      <button
                        onClick={() => handleEditClick(book)}
                        className="text-blue-600 hover:underline text-sm"
                      >
                        Edit
                      </button>
                      <button
                        onClick={() => handleDelete(book.id)}
                        className="text-red-600 hover:underline text-sm"
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
