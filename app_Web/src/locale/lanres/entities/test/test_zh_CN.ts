import Test_zh_CN_Base from './test_zh_CN_base';

function getLocaleResource(){
    const Test_zh_CN_OwnData = {};
    const targetData = Object.assign(Test_zh_CN_Base(), Test_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;