import Action_zh_CN_Base from './action_zh_CN_base';

function getLocaleResource(){
    const Action_zh_CN_OwnData = {};
    const targetData = Object.assign(Action_zh_CN_Base(), Action_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;