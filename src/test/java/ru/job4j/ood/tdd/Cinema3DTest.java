package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Disabled("����� ���������. ������� ��������� ����� ���������� ���� ������� �� �������.")
public class Cinema3DTest {
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(ses -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyOnInvalidColumnThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, 1, -1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyOnInvalidDateThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = new GregorianCalendar(1970, Calendar.JANUARY, 1);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyCloseSeatThenGetException() {

        Cinema cinema = new Cinema3D();
        Account account = new AccountCinema();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyWithOutAccountThenGetException() {
        Cinema cinema = new Cinema3D();
        Calendar date = new GregorianCalendar(1970, Calendar.JANUARY, 1);
        assertThatThrownBy(() -> cinema.buy(null, 1, 1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }
}