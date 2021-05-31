import PSSysSFPub_zh_CN_Base from './pssys-sfpub_zh_CN_base';

function getLocaleResource(){
    const PSSysSFPub_zh_CN_OwnData = {};
    const targetData = Object.assign(PSSysSFPub_zh_CN_Base(), PSSysSFPub_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;