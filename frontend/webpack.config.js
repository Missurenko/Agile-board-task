var path = require('path');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');

const PATHS = {
    source: path.join(__dirname, 'src'),
    build: path.join(__dirname, 'build')
};

module.exports = {
    entry: [
        PATHS.source + '/index.jsx'
    ],
    output: {
        path: PATHS.build,
        filename: 'bundle.js'
    },
    resolve: {
        extensions: ['', '.js', '.jsx'],
        root: path.join(__dirname, 'src')
    },
    module: {
        loaders: [
            {
                test: /\.jsx?/,
                include: PATHS.source,
                loader: 'babel-loader'
            },
            {
                test: /\.js?/,
                include: PATHS.source,
                loader: 'babel-loader'
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            }
        ],
    }
};
