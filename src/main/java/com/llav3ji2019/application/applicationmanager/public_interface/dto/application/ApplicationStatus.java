package com.llav3ji2019.application.applicationmanager.public_interface.dto.application;

public enum ApplicationStatus {
    /*
        Черновик
     */
    DRAFT,
    /*
        Заявка создана, но не отправлена
        (мб это тоже должно быть черновиком, но я решил вынести)
     */
    NOT_SENT,
    /*
        Пользователь отправил заявку к оператору
     */
    SENT,
    /*
        Оператор принял заявку
     */
    ACCEPTED,
    /*
        Оператор отклонил заявку
     */
    DENIED
}
