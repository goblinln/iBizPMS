import IBZProToDoAction_zh_CN_Base from './ibzpro-to-do-action_zh_CN_base';

function getLocaleResource(){
    const IBZProToDoAction_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZProToDoAction_zh_CN_Base(), IBZProToDoAction_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;