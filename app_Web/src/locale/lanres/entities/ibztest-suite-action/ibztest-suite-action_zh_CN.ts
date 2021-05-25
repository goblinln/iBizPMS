import IBZTestSuiteAction_zh_CN_Base from './ibztest-suite-action_zh_CN_base';

function getLocaleResource(){
    const IBZTestSuiteAction_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZTestSuiteAction_zh_CN_Base(), IBZTestSuiteAction_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;