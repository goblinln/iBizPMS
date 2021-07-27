<template>
    <div
        class="el-tree"
        :class="{
            'el-tree--highlight-current': highlightCurrent,
            'is-dragging': !!dragState.draggingNode,
            'is-drop-not-allow': !dragState.allowDrop,
            'is-drop-inner': dragState.dropType === 'inner',
        }"
        role="tree"
    >
        <app-element-tree-node
            v-for="child in root.childNodes"
            :node="child"
            :props="props"
            :render-after-expand="renderAfterExpand"
            :show-checkbox="showCheckbox"
            :key="getNodeKey(child)"
            :render-content="renderContent"
            @node-expand="handleNodeExpand"
        >
        </app-element-tree-node>
        <div class="el-tree__empty-block" v-if="isEmpty">
            <span class="el-tree__empty-text">{{ emptyText }}</span>
        </div>
        <div v-show="dragState.showDropIndicator" class="el-tree__drop-indicator" ref="dropIndicator"></div>
    </div>
</template>
<script>
import { Tree } from 'element-ui';
import AppElementTreeNode from './app-element-tree-node.vue';
export default {
    mixins: [Tree],
    components: {
        AppElementTreeNode
    },
    created() {
        let dragState = this.dragState;
        this.$on('tree-node-drag-start', async (event, treeNode) => {
            if (typeof this.allowDrag === 'function' && !(await this.allowDrag(treeNode.node))) {
                event.preventDefault();
                return false;
            }
            event.dataTransfer.effectAllowed = 'move';
            // wrap in try catch to address IE's error when first param is 'text/plain'
            try {
                // setData is required for draggable to work in FireFox
                // the content has to be '' so dragging a node out of the tree won't open a new tab in FireFox
                event.dataTransfer.setData('text/plain', '');
            } catch (e) {}
            dragState.draggingNode = treeNode;
            this.$emit('node-drag-start', treeNode.node, event);
        });
        this.$on('tree-node-drag-over', async (event, treeNode) => {
            const dropNode = this.findNearestComponent(event.target, 'AppElementTreeNode');
            const oldDropNode = dragState.dropNode;
            if (oldDropNode && oldDropNode !== dropNode) {
                this.removeClass(oldDropNode.$el, 'is-drop-inner');
            }
            const draggingNode = dragState.draggingNode;
            if (!draggingNode || !dropNode) return;
            let dropPrev = true;
            let dropInner = true;
            let dropNext = true;
            let userAllowDropInner = true;
            if (typeof this.allowDrop === 'function') {
                dropPrev = await this.allowDrop(draggingNode.node, dropNode.node, 'prev');
                userAllowDropInner = dropInner = await this.allowDrop(draggingNode.node, dropNode.node, 'inner');
                dropNext = await this.allowDrop(draggingNode.node, dropNode.node, 'next');
            }
            event.dataTransfer.dropEffect = dropInner ? 'move' : 'none';
            if ((dropPrev || dropInner || dropNext) && oldDropNode !== dropNode) {
                if (oldDropNode) {
                    this.$emit('node-drag-leave', draggingNode.node, oldDropNode.node, event);
                }
                this.$emit('node-drag-enter', draggingNode.node, dropNode.node, event);
            }
            if (dropPrev || dropInner || dropNext) {
                dragState.dropNode = dropNode;
            }
            if (dropNode.node.nextSibling === draggingNode.node) {
                dropNext = false;
            }
            if (dropNode.node.previousSibling === draggingNode.node) {
                dropPrev = false;
            }
            if (dropNode.node.contains(draggingNode.node, false)) {
                dropInner = false;
            }
            if (draggingNode.node === dropNode.node || draggingNode.node.contains(dropNode.node)) {
                dropPrev = false;
                dropInner = false;
                dropNext = false;
            }
            const targetPosition = dropNode.$el.getBoundingClientRect();
            const treePosition = this.$el.getBoundingClientRect();
            let dropType;
            const prevPercent = dropPrev ? (dropInner ? 0.25 : dropNext ? 0.45 : 1) : -1;
            const nextPercent = dropNext ? (dropInner ? 0.75 : dropPrev ? 0.55 : 0) : 1;
            let indicatorTop = -9999;
            const distance = event.clientY - targetPosition.top;
            if (distance < targetPosition.height * prevPercent) {
                dropType = 'before';
            } else if (distance > targetPosition.height * nextPercent) {
                dropType = 'after';
            } else if (dropInner) {
                dropType = 'inner';
            } else {
                dropType = 'none';
            }
            const iconPosition = dropNode.$el.querySelector('.el-tree-node__expand-icon').getBoundingClientRect();
            const dropIndicator = this.$refs.dropIndicator;
            if (dropType === 'before') {
                indicatorTop = iconPosition.top - treePosition.top;
            } else if (dropType === 'after') {
                indicatorTop = iconPosition.bottom - treePosition.top;
            }
            dropIndicator.style.top = indicatorTop + 'px';
            dropIndicator.style.left = iconPosition.right - treePosition.left + 'px';
            if (dropType === 'inner') {
                this.addClass(dropNode.$el, 'is-drop-inner');
            } else {
                this.removeClass(dropNode.$el, 'is-drop-inner');
            }
            dragState.showDropIndicator = dropType === 'before' || dropType === 'after';
            dragState.allowDrop = dragState.showDropIndicator || userAllowDropInner;
            dragState.dropType = dropType;
            this.$emit('node-drag-over', draggingNode.node, dropNode.node, event);
        });
    },
    methods: {
        findNearestComponent(element, componentName) {
            let target = element;
            while (target && target.tagName !== 'BODY') {
                if (target.__vue__ && target.__vue__.$options.name === componentName) {
                    return target.__vue__;
                }
                target = target.parentNode;
            }
            return null;
        },
        removeClass(el, cls) {
            if (!el || !cls) return;
            var classes = cls.split(' ');
            var curClass = ' ' + el.className + ' ';

            for (var i = 0, j = classes.length; i < j; i++) {
                var clsName = classes[i];
                if (!clsName) continue;

                if (el.classList) {
                    el.classList.remove(clsName);
                } else if (hasClass(el, clsName)) {
                    curClass = curClass.replace(' ' + clsName + ' ', ' ');
                }
            }
            if (!el.classList) {
                el.setAttribute('class', trim(curClass));
            }
        },
        addClass(el, cls) {
            if (!el) return;
            var curClass = el.className;
            var classes = (cls || '').split(' ');

            for (var i = 0, j = classes.length; i < j; i++) {
                var clsName = classes[i];
                if (!clsName) continue;

                if (el.classList) {
                    el.classList.add(clsName);
                } else if (!hasClass(el, clsName)) {
                    curClass += ' ' + clsName;
                }
            }
            if (!el.classList) {
                el.setAttribute('class', curClass);
            }
        },
    },
};
</script>