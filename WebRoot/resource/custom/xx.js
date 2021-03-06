// Image Picker
// by Rodrigo Vera
//
// Version 0.1.4
// Full source at https://github.com/rvera/image-picker
// MIT License, https://github.com/rvera/image-picker/blob/master/LICENSE
(function() {
    var t, e, i, s, n = [].indexOf ||
    function(t) {
        for (var e = 0,
        i = this.length; i > e; e++) if (e in this && this[e] === t) return e;
        return - 1
    };
    jQuery.fn.extend({
        imagepicker: function(e) {
            return null == e && (e = {}),
            this.each(function() {
                var i;
                return i = jQuery(this),
                i.next("ul.image_picker_selector").remove(),
                i.data("picker", new t(this, s(e))),
                null != e.initialized ? e.initialized() : void 0
            })
        }
    }),
    s = function(t) {
        var e;
        return e = {
            hide_select: !0,
            show_label: !1,
            initialized: void 0,
            changed: void 0,
            clicked: void 0,
            selected: void 0,
            limit: void 0,
            limit_reached: void 0
        },
        jQuery.extend(e, t)
    },
    i = function(t, e) {
        return 0 === jQuery(t).not(e).length && 0 === jQuery(e).not(t).length
    },
    t = function() {
        function t(t, e) {
            this.opts = null != e ? e: {},
            this.select = jQuery(t),
            this.multiple = "multiple" === this.select.attr("multiple"),
            null != this.select.data("limit") && (this.opts.limit = parseInt(this.select.data("limit"))),
            this.build_and_append_picker()
        }
        return t.prototype.build_and_append_picker = function() {
            return this.opts.hide_select && this.select.hide(),
            this.select.change({
                picker: this
            },
            function(t) {
                return t.data.picker.sync_picker_with_select()
            }),
            null != this.picker && this.picker.remove(),
            this.create_picker(),
            this.select.after(this.picker),
            this.sync_picker_with_select()
        },
        t.prototype.sync_picker_with_select = function() {
            var t, e, i, s, n;
            for (s = this.picker_options, n = [], e = 0, i = s.length; i > e; e++) t = s[e],
            t.is_selected() ? n.push(t.mark_as_selected()) : n.push(t.unmark_as_selected());
            return n
        },
        t.prototype.create_picker = function() {
            var t, i, s, n;
            for (this.picker = jQuery("<ul class='thumbnails image_picker_selector'></ul>"), this.picker_options = function() {
                var i, s, n, l;
                for (n = this.select.find("option"), l = [], i = 0, s = n.length; s > i; i++) t = n[i],
                l.push(new e(t, this, this.opts));
                return l
            }.call(this), n = this.picker_options, i = 0, s = n.length; s > i; i++) t = n[i],
            t.has_image() && this.picker.append(t.node);
            return this.picker
        },
        t.prototype.has_implicit_blanks = function() {
            var t;
            return function() {
                var e, i, s, n;
                for (s = this.picker_options, n = [], e = 0, i = s.length; i > e; e++) t = s[e],
                t.is_blank() && !t.has_image() && n.push(t);
                return n
            }.call(this).length > 0
        },
        t.prototype.selected_values = function() {
            return this.multiple ? this.select.val() || [] : [this.select.val()]
        },
        t.prototype.toggle = function(t) {
            var e, s, l;
            return s = this.selected_values(),
            this.multiple ? (l = t.value(), n.call(this.selected_values(), l) >= 0 ? t.option.prop("selected", !1) : null != this.opts.limit ? this.selected_values().length < this.opts.limit ? t.option.prop("selected", !0) : null != this.opts.limit_reached && this.opts.limit_reached() : t.option.prop("selected", !0)) : this.has_implicit_blanks() && t.is_selected() ? this.select.val("") : this.select.val(t.value()),
            e = this.selected_values(),
            i(s, e) || (this.select.change(), null == this.opts.changed) ? void 0 : this.opts.changed()
        },
        t
    } (),
    e = function() {
        function t(t, e, i) {
            this.picker = e,
            this.opts = null != i ? i: {},
            this.option = jQuery(t),
            this.create_node()
        }
        return t.prototype.has_image = function() {
            return null != this.option.data("img-src")
        },
        t.prototype.is_blank = function() {
            return ! (null != this.value() && "" !== this.value())
        },
        t.prototype.is_selected = function() {
            var t;
            return t = this.picker.select.val(),
            this.picker.multiple ? jQuery.inArray(this.value(), t) >= 0 : this.value() === t
        },
        t.prototype.mark_as_selected = function() {
            return this.node.find(".thumbnail").addClass("selected")
        },
        t.prototype.unmark_as_selected = function() {
            return this.node.find(".thumbnail").removeClass("selected")
        },
        t.prototype.value = function() {
            return this.option.val()
        },
        t.prototype.label = function() {
            return this.option.data("img-label") ? this.option.data("img-label") : this.option.text()
        },
        t.prototype.clicked = function() {
            return this.picker.toggle(this),
            null != this.opts.clicked && this.opts.clicked(),
            null != this.opts.selected && this.is_selected() ? this.opts.selected() : void 0
        },
        t.prototype.create_node = function() {
            var t, e;
            return this.node = jQuery("<li/>"),
            t = jQuery("<img class='image_picker_image'/>"),
            t.attr("src", this.option.data("img-src")),
            e = jQuery("<div class='thumbnail'>"),
            e.click({
                option: this
            },
            function(t) {
                return t.data.option.clicked()
            }),
            e.append(t),
            this.opts.show_label && e.append(jQuery("<p/>").html(this.label())),
            this.node.append(e),
            this.node
        },
        t
    } ()
}).call(this);