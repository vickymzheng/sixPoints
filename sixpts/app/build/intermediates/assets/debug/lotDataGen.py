#!/usr/bin/python

from optparse import OptionParser, Option, OptionValueError
import sys, os
import timeit, time
import re
from os.path import dirname
from Bio import SeqIO

def parse():
    
    lots = ['JacobsA', 'JacobsB', 'JacobsC', 'JarvisA', 'JarvisB', 'JarvisC', \
        'BairdA', 'BairdB', 'CookeA', 'CookeB', 'Furnas', 'FronczakA', 'FronczakB', \
        'GovernorsA', 'GovernorsB', 'GovernorsC', 'GovernorsD', 'GovernorsE', \
        'HochstetterA', 'HochstetterB', 'SleeA', 'SleeB', 'SpecialEvents']
    months = ['0', '1', '2', '3', '4', '5', '6', \
        '7', '8', '9', '10', '11']
    days = ['1', '2', '3', '4', '5', '6', '7']
    hour = ['12', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11']
    minute = ['0', '30']
    ampm = ['0', '1']
    rating = ['1', '99', '1', '99', '1', '97', '1', '96', '1', '93', '1', '91', '1', '87', '1', '84', '1', \
            '72', '2', '63', '8', '60', '9', '54', '9', '51', '12', '49', '26', '41', '27', '33', '39', \
            '27', '68', '13', '73', '11', '76', '8', '91', '7', '96', '5', '99', '4', '99', '3']

    for l in lots:
        lotFile = open(str(l) + ".txt", 'w+')
        for m in months:
            for d in days:
                count = 0
                for h in hour:
                    for i in minute:
                        for a in ampm:
                            lotFile.write(str(m) + str(d) + str(h) + str(i) + str(a) + ' ' + str(rating[count]) + '\n')
                            count = count + 1

def main():
    '''
    Main
    '''
    parse()
if __name__ == "__main__":
    main()
