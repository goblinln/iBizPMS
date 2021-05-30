import TestRun_zh_CN_Base from './test-run_zh_CN_base';

function getLocaleResource(){
    const TestRun_zh_CN_OwnData = {};
    const targetData = Object.assign(TestRun_zh_CN_Base(), TestRun_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;