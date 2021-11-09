//webpack.config.js
const path = require('path');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');

module.exports = {
    mode: "production",
    // target: ['node', 'es5'],
    // devtool: "source-map",
    entry: "./src/index.ts",
    output: {
        path: path.resolve(__dirname, './build'),
        filename: "bundle.js" // <--- Will be compiled to this single file
    },
    resolve: {
        extensions: [".ts"],
    },
    module: {
        rules: [
            {
                loader: 'ts-loader'
            }
        ]
    },
    optimization: {
        minimize: false
    }
};