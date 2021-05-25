import IbzProBuildAction_en_US_Base from './ibz-pro-build-action_en_US_base';

function getLocaleResource(){
    const IbzProBuildAction_en_US_OwnData = {};
    const targetData = Object.assign(IbzProBuildAction_en_US_Base(), IbzProBuildAction_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;