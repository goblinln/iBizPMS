import IBZProReleaseAction_zh_CN_Base from './ibzpro-release-action_zh_CN_base';

function getLocaleResource(){
    const IBZProReleaseAction_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZProReleaseAction_zh_CN_Base(), IBZProReleaseAction_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;