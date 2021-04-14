import SuiteCase_zh_CN_Base from './suite-case_zh_CN_base';

function getLocaleResource(){
    const SuiteCase_zh_CN_OwnData = {};
    const targetData = Object.assign(SuiteCase_zh_CN_Base(), SuiteCase_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;