System.register(['./p-28478b38.system.js', './p-9e12c5dd.system.js'], function (o) {
    'use strict';
    var e, i, t, n, r, s, a;
    return {
        setters: [
            function (o) {
                e = o.r;
                i = o.h;
                t = o.H;
                n = o.c;
            },
            function (o) {
                r = o.i;
                s = o.g;
                a = o.b;
            },
        ],
        execute: function () {
            var c = function (o) {
                if (o && typeof document !== 'undefined') {
                    var e = document.createElement('div');
                    e.innerHTML = o;
                    for (var i = e.childNodes.length - 1; i >= 0; i--) {
                        if (e.childNodes[i].nodeName.toLowerCase() !== 'svg') {
                            e.removeChild(e.childNodes[i]);
                        }
                    }
                    var t = e.firstElementChild;
                    if (t && t.nodeName.toLowerCase() === 'svg') {
                        var n = t.getAttribute('class') || '';
                        t.setAttribute('class', (n + ' s-ion-icon').trim());
                        if (l(t)) {
                            return e.innerHTML;
                        }
                    }
                }
                return '';
            };
            var l = function (o) {
                if (o.nodeType === 1) {
                    if (o.nodeName.toLowerCase() === 'script') {
                        return false;
                    }
                    for (var e = 0; e < o.attributes.length; e++) {
                        var i = o.attributes[e].value;
                        if (r(i) && i.toLowerCase().indexOf('on') === 0) {
                            return false;
                        }
                    }
                    for (var e = 0; e < o.childNodes.length; e++) {
                        if (!l(o.childNodes[e])) {
                            return false;
                        }
                    }
                }
                return true;
            };
            var f = new Map();
            var d = new Map();
            var h = function (o) {
                var e = d.get(o);
                if (!e) {
                    if (typeof fetch !== 'undefined') {
                        e = fetch(o).then(function (e) {
                            if (e.ok) {
                                return e.text().then(function (e) {
                                    f.set(o, c(e));
                                });
                            }
                            f.set(o, '');
                        });
                        d.set(o, e);
                    } else {
                        f.set(o, '');
                        return Promise.resolve();
                    }
                }
                return e;
            };
            var u =
                ':host{display:inline-block;width:1em;height:1em;contain:strict;fill:currentColor;-webkit-box-sizing:content-box !important;box-sizing:content-box !important}:host .ionicon{stroke:currentColor}.ionicon-fill-none{fill:none}.ionicon-stroke-width{stroke-width:32px;stroke-width:var(--ionicon-stroke-width, 32px)}.icon-inner,.ionicon,svg{display:block;height:100%;width:100%}:host(.flip-rtl) .icon-inner{-webkit-transform:scaleX(-1);transform:scaleX(-1)}:host(.icon-small){font-size:18px !important}:host(.icon-large){font-size:32px !important}:host(.ion-color){color:var(--ion-color-base) !important}:host(.ion-color-primary){--ion-color-base:var(--ion-color-primary, #3880ff)}:host(.ion-color-secondary){--ion-color-base:var(--ion-color-secondary, #0cd1e8)}:host(.ion-color-tertiary){--ion-color-base:var(--ion-color-tertiary, #f4a942)}:host(.ion-color-success){--ion-color-base:var(--ion-color-success, #10dc60)}:host(.ion-color-warning){--ion-color-base:var(--ion-color-warning, #ffce00)}:host(.ion-color-danger){--ion-color-base:var(--ion-color-danger, #f14141)}:host(.ion-color-light){--ion-color-base:var(--ion-color-light, #f4f5f8)}:host(.ion-color-medium){--ion-color-base:var(--ion-color-medium, #989aa2)}:host(.ion-color-dark){--ion-color-base:var(--ion-color-dark, #222428)}';
            var v = o(
                'ion_icon',
                (function () {
                    function o(o) {
                        e(this, o);
                        this.isVisible = false;
                        this.mode = b();
                        this.lazy = false;
                    }
                    o.prototype.connectedCallback = function () {
                        var o = this;
                        this.waitUntilVisible(this.el, '50px', function () {
                            o.isVisible = true;
                            o.loadIcon();
                        });
                    };
                    o.prototype.disconnectedCallback = function () {
                        if (this.io) {
                            this.io.disconnect();
                            this.io = undefined;
                        }
                    };
                    o.prototype.waitUntilVisible = function (o, e, i) {
                        var t = this;
                        if (this.lazy && typeof window !== 'undefined' && window.IntersectionObserver) {
                            var n = (this.io = new window.IntersectionObserver(
                                function (o) {
                                    if (o[0].isIntersecting) {
                                        n.disconnect();
                                        t.io = undefined;
                                        i();
                                    }
                                },
                                { rootMargin: e }
                            ));
                            n.observe(o);
                        } else {
                            i();
                        }
                    };
                    o.prototype.loadIcon = function () {
                        var o = this;
                        if (this.isVisible) {
                            var e = s(this);
                            if (e) {
                                if (f.has(e)) {
                                    this.svgContent = f.get(e);
                                } else {
                                    h(e).then(function () {
                                        return (o.svgContent = f.get(e));
                                    });
                                }
                            }
                        }
                        if (!this.ariaLabel && this.ariaHidden !== 'true') {
                            var i = a(this.name, this.icon, this.mode, this.ios, this.md);
                            if (i) {
                                this.ariaLabel = i.replace(/\-/g, ' ');
                            }
                        }
                    };
                    o.prototype.render = function () {
                        var o, e;
                        var n = this.mode || 'md';
                        var r =
                            this.flipRtl ||
                            (this.ariaLabel &&
                                (this.ariaLabel.indexOf('arrow') > -1 || this.ariaLabel.indexOf('chevron') > -1) &&
                                this.flipRtl !== false);
                        return i(
                            t,
                            {
                                role: 'img',
                                class: Object.assign(
                                    Object.assign(((o = {}), (o[n] = true), o), m(this.color)),
                                    ((e = {}),
                                    (e['icon-' + this.size] = !!this.size),
                                    (e['flip-rtl'] = !!r && this.el.ownerDocument.dir === 'rtl'),
                                    e)
                                ),
                            },
                            this.svgContent
                                ? i('div', { class: 'icon-inner', innerHTML: this.svgContent })
                                : i('div', { class: 'icon-inner' })
                        );
                    };
                    Object.defineProperty(o, 'assetsDirs', {
                        get: function () {
                            return ['svg'];
                        },
                        enumerable: false,
                        configurable: true,
                    });
                    Object.defineProperty(o.prototype, 'el', {
                        get: function () {
                            return n(this);
                        },
                        enumerable: false,
                        configurable: true,
                    });
                    Object.defineProperty(o, 'watchers', {
                        get: function () {
                            return { name: ['loadIcon'], src: ['loadIcon'], icon: ['loadIcon'] };
                        },
                        enumerable: false,
                        configurable: true,
                    });
                    return o;
                })()
            );
            var b = function () {
                return (typeof document !== 'undefined' && document.documentElement.getAttribute('mode')) || 'md';
            };
            var m = function (o) {
                var e;
                return o ? ((e = { 'ion-color': true }), (e['ion-color-' + o] = true), e) : null;
            };
            v.style = u;
        },
    };
});
