const {
    override,
    addLessLoader,
 } = require('customize-cra');
  
 module.exports = override(
    // less 的配置
    addLessLoader(),
 );