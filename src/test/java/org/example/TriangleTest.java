package org.example;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
/*Для использования параметризованных тестов используем библиотеку DataProvider
для этого добавил в dependency следующий блок:
    <dependency>
      <groupId>com.tngtech.java</groupId>
      <artifactId>junit-dataprovider</artifactId>
      <version>1.13.1</version>
      <scope>test</scope>
    </dependency>
    <dependency>
 */
@RunWith(DataProviderRunner.class)
public class TriangleTest {
    Triangle triangle;

    //Создаем DataProvider и передаем ему параметры
    @DataProvider
    public static Object[][] attributesForArea() {
        return new Object[][]{
                {Triangle.createTriangle("one", 2.5, 2.5, 3.0), 3.0},
                {Triangle.createTriangle("two", 1, 1, 1), 0.4330},
                {Triangle.createTriangle("three", 300, 300, 150), 21785.5313},
        };
    }

    @After
    public void tearDown() {
        triangle = null;
    }

    @Test
    @UseDataProvider("attributesForArea")
    public void getAreaTest(Triangle triangle, double expected) {
        double delta = 0.0001;
        double actual = triangle.getArea();

        Assert.assertEquals(expected, actual, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTriangleBuilder1() {
        triangle = Triangle.createTriangle("one", 0, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTriangleBuilder2() {
        triangle = Triangle.createTriangle("two", -1, -1, -1.5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTriangleBuilder3() {
        triangle = Triangle.createTriangle("three", 150, 3, 2);
    }

    @Test(timeout = 1000)
    public void runForever() {
        while (true);
    }
}