import TestReult_zh_CN_Base from './test-reult_zh_CN_base';

function getLocaleResource(){
    const TestReult_zh_CN_OwnData = {};
    const targetData = Object.assign(TestReult_zh_CN_Base(), TestReult_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;