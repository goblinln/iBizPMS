import IBZCaseAction_zh_CN_Base from './ibzcase-action_zh_CN_base';

function getLocaleResource(){
    const IBZCaseAction_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZCaseAction_zh_CN_Base(), IBZCaseAction_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;