// Author: Matúš Čongrády

import { REST_API_URL } from '@/contants';
import { request } from '@/utils/fetch';

export const getBooks = () => {
  return request(`${REST_API_URL}/books`);
};

interface BookData {
  name: string;
  author: string;
  isbn: string;
}

export const createBook = (bookData: BookData) => {
  return request(`${REST_API_URL}/books/create`, { method: 'POST', body: JSON.stringify(bookData) });
};

export const editBook = (bookData: BookData, id: string) => {
  return request(`${REST_API_URL}/books/edit`, { method: 'POST', body: JSON.stringify({ ...bookData, id }) });
};

export const deleteBook = (id: string) => {
  return request(`${REST_API_URL}/books/${id}`, { method: 'DELETE' });
};
