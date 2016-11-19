module.exports = {
  devtool: "source-map",
  entry: './scripts/App.ts',
  output: {
    filename: 'bundle.js',
    sourceMapFilename: "bundle.js.map",
  },
  resolve: {
    extensions: ['.ts', '', '.webpack.js', '.web.js', '.js']
  },
  module: {
    loaders: [
      { test: /\.ts$/, loader: 'ts-loader' }
    ]
  }
}