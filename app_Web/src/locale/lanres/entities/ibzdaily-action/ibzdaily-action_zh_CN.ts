import IBZDailyAction_zh_CN_Base from './ibzdaily-action_zh_CN_base';

function getLocaleResource(){
    const IBZDailyAction_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZDailyAction_zh_CN_Base(), IBZDailyAction_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;