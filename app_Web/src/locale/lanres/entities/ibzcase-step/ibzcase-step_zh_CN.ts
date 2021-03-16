import IBZCaseStep_zh_CN_Base from './ibzcase-step_zh_CN_base';

function getLocaleResource(){
    const IBZCaseStep_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZCaseStep_zh_CN_Base(), IBZCaseStep_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;