import IBZStoryAction_zh_CN_Base from './ibzstory-action_zh_CN_base';

function getLocaleResource(){
    const IBZStoryAction_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZStoryAction_zh_CN_Base(), IBZStoryAction_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;