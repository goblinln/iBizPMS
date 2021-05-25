import IBZProWeeklyAction_zh_CN_Base from './ibzpro-weekly-action_zh_CN_base';

function getLocaleResource(){
    const IBZProWeeklyAction_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZProWeeklyAction_zh_CN_Base(), IBZProWeeklyAction_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;