import IbzProBugAction_zh_CN_Base from './ibz-pro-bug-action_zh_CN_base';

function getLocaleResource(){
    const IbzProBugAction_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzProBugAction_zh_CN_Base(), IbzProBugAction_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;