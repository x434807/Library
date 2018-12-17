// Author: Matúš Čongrády

import { REST_API_URL } from '@/contants';
import { request } from '@/utils/fetch';

export const getBooks = () => {
  return request(`${REST_API_URL}/books`);
};
