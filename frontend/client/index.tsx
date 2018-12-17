// Author: Matúš Čongrády

import * as React from 'react';
import { render } from 'react-dom';
import App from './App';

render(<App />, document.getElementById('app'));
window.onhashchange = () => {
  render(<App />, document.getElementById('app'));
};
