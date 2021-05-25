import IBZProWeeklyAction_en_US_Base from './ibzpro-weekly-action_en_US_base';

function getLocaleResource(){
    const IBZProWeeklyAction_en_US_OwnData = {};
    const targetData = Object.assign(IBZProWeeklyAction_en_US_Base(), IBZProWeeklyAction_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;