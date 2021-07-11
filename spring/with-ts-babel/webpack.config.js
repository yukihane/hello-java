"use strict";
// Generated using webpack-cli https://github.com/webpack/webpack-cli

const path = require("path");

const isProduction = process.env.NODE_ENV == "production";

const config = {
  entry: "./src/main/js/index.ts",
  output: {
    path: path.resolve(__dirname, "build/resources/main/static"),
  },
  plugins: [
    // Add your plugins here
    // Learn more about plugins from https://webpack.js.org/configuration/plugins/
  ],
  module: {
    rules: [
      {
        loader: "babel-loader",
        options: {
          exclude: [
            // \\ for Windows, \/ for Mac OS and Linux
            /node_modules[\\\/]core-js/,
            /node_modules[\\\/]webpack[\\\/]buildin/,
          ],
          presets: [
            [
              "@babel/preset-env",
              {
                useBuiltIns: "usage",
                corejs: "3",
                shippedProposals: true,
              },
            ],
            "@babel/preset-typescript",
          ],
          plugins: ["@babel/plugin-proposal-class-properties"],
        },
      },

      // Add your rules for custom modules here
      // Learn more about loaders from https://webpack.js.org/loaders/
    ],
  },
  resolve: {
    extensions: [".tsx", ".ts", ".js"],
  },
};

module.exports = () => {
  if (isProduction) {
    config.mode = "production";
  } else {
    config.mode = "development";
    config.devtool = "inline-source-map";
  }
  return config;
};
