import IbzProBugAction_en_US_Base from './ibz-pro-bug-action_en_US_base';

function getLocaleResource(){
    const IbzProBugAction_en_US_OwnData = {};
    const targetData = Object.assign(IbzProBugAction_en_US_Base(), IbzProBugAction_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;