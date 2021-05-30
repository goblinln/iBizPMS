
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppListBase } from 'ibiz-vue/src/components/control/app-common-control/app-list-base';
import { Environment } from '@/environments/environment';
import axios from 'axios';

import '../plugin-style.less';

/**
 * 文件列表下载绘制插件插件类
 *
 * @export
 * @class ListDownload
 * @class ListDownload
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class ListDownload extends AppListBase {

    /**
     * 计算文件mime类型
     *
     * @param filetype 文件后缀
     * @memberof DiskFileUpload
     */
     public calcFilemime(filetype: string): string {
        let mime = 'image/png';
        switch (filetype) {
            case '.wps':
                mime = 'application/kswps';
                break;
            case '.doc':
                mime = 'application/msword';
                break;
            case '.docx':
                mime = 'application/vnd.openxmlformats-officedocument.wordprocessingml.document';
                break;
            case '.txt':
                mime = 'text/plain';
                break;
            case '.zip':
                mime = 'application/zip';
                break;
            case '.png':
                mime = 'imgage/png';
                break;
            case '.gif':
                mime = 'image/gif';
                break;
            case '.jpeg':
                mime = 'image/jpeg';
                break;
            case '.jpg':
                mime = 'image/jpeg';
                break;
            case '.rtf':
                mime = 'application/rtf';
                break;
            case '.avi':
                mime = 'video/x-msvideo';
                break;
            case '.gz':
                mime = 'application/x-gzip';
                break;
            case '.tar':
                mime = 'application/x-tar';
                break;
        }
        return mime;
    }
    
    /**
     * 下载文件
     *
     * @param srfkey 文件ID
     * @memberof DiskFileUpload
     */
    public DownloadFile(srfkey: string) {
        const url = `${Environment.ExportFile}/${srfkey}`;
        // 发送get请求
        axios({
            method: 'get',
            url: url,
            responseType: 'blob',
        })
        .then((response: any) => {
            if (!response || response.status != 200) {
                console.error('图片下载失败！');
                return;
            }
            // 请求成功，后台返回的是一个文件流
            if (response.data) {
                // 获取文件名
                const disposition = response.headers['content-disposition'];
                const filename = disposition.split('filename=')[1];
                const ext = '.' + filename.split('.').pop();
                let filetype = this.calcFilemime(ext);
                // 用blob对象获取文件流
                let blob = new Blob([response.data], { type: filetype });
                // 通过文件流创建下载链接
                var href = URL.createObjectURL(blob);
                // 创建一个a元素并设置相关属性
                let a = document.createElement('a');
                a.href = href;
                a.download = filename;
                // 添加a元素到当前网页
                document.body.appendChild(a);
                // 触发a元素的点击事件，实现下载
                a.click();
                // 从当前网页移除a元素
                document.body.removeChild(a);
                // 释放blob对象
                URL.revokeObjectURL(href);
            } else {
                console.error('图片下载失败！');
            }
        })
        .catch((error: any) => {
            console.error(error);
        });
    }

	/**
     * 绘制
     * 
     * @memberof ListDownload
     */
    public render(): any{
        if(!this.controlIsLoaded){
            return null;
        }
        let listClass: any = 'app-list';
        if(this.items.length <= 0){
            listClass = 'app-list app-list-empty';
        }
        return <div class={listClass}>
            {this.items.map((item: any)=>{
                let itemClass: any = 'app-list-item list-download';
                if(item.isselected){
                    itemClass = 'app-list-item isSelect';
                }
                return <div class={itemClass} on-click={()=>{this.handleClick(item)}} on-dblclick={()=>{this.handleDblClick(item)}}>
                    <a on-click={()=>{this.DownloadFile(item.srfkey)}}>{item.srfmajortext}</a>&nbsp;<icon type="md-close" on-click={()=>{this.remove([item])}} />
                </div>
            })}
        </div>
    }

}

