import IbzLibCasesteps_en_US_Base from './ibz-lib-casesteps_en_US_base';

function getLocaleResource(){
    const IbzLibCasesteps_en_US_OwnData = {};
    const targetData = Object.assign(IbzLibCasesteps_en_US_Base(), IbzLibCasesteps_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;