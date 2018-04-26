import React from 'react';
import {Provider} from 'react-redux'
import ReactDOM from 'react-dom';
import App from './containers/App/index';

import './css/style.css';

import configureStore from './store/configureStrore';

export const store = configureStore();

ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('root')
);