import IBZProProjectAction_zh_CN_Base from './ibzpro-project-action_zh_CN_base';

function getLocaleResource(){
    const IBZProProjectAction_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZProProjectAction_zh_CN_Base(), IBZProProjectAction_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;