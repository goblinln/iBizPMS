import PSSysSFPub_en_US_Base from './pssys-sfpub_en_US_base';

function getLocaleResource(){
    const PSSysSFPub_en_US_OwnData = {};
    const targetData = Object.assign(PSSysSFPub_en_US_Base(), PSSysSFPub_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;