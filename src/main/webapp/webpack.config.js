var webpack = require('webpack');  

module.exports = {
  devtool: "source-map",
  entry: './scripts/App.ts',
  output: {
    filename: 'bundle.js',
    sourceMapFilename: "bundle.js.map",
  },
  devtool: 'source-map',
  resolve: {
    extensions: [ '', '.webpack.js', '.web.js', '.ts', '.js']
  },
  plugins: [
   // new webpack.optimize.UglifyJsPlugin()
  ],
  module: {
    loaders: [
      { test: /\.ts$/, loader: 'ts' }
    ]
  }
}