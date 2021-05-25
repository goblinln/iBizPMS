import IbzProBuildAction_zh_CN_Base from './ibz-pro-build-action_zh_CN_base';

function getLocaleResource(){
    const IbzProBuildAction_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzProBuildAction_zh_CN_Base(), IbzProBuildAction_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;