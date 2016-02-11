module.exports = {
    show: function (success, failure, options) {
        options = options || {};
        var hours = options.hours || -1;
        var minutes = options.minutes || -1;
        var args = [hours, minutes];
        cordova.exec(success, failure, 'DurationPicker', 'show', args);
    }
};
