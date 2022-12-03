cordova.define('cordova/plugin_list', function(require, exports, module) {
  module.exports = [
    {
      "id": "io.cordova.configurer.ConfigCenter",
      "file": "plugins/io.cordova.configurer/www/ConfigCenter.js",
      "pluginId": "io.cordova.configurer",
      "clobbers": [
        "cordova.plugins.ConfigCenter"
      ]
    }
  ];
  module.exports.metadata = {
    "io.cordova.configurer": "0.0.1"
  };
});