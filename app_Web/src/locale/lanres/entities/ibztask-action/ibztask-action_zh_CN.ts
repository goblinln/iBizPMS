import IBZTaskAction_zh_CN_Base from './ibztask-action_zh_CN_base';

function getLocaleResource(){
    const IBZTaskAction_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZTaskAction_zh_CN_Base(), IBZTaskAction_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;